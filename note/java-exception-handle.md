(筆記) 笑談軟體工程：例外處理設計的逆襲
=====

---
## **例外處理設計**
### 1. 低階層的例外轉成高階層所理解的例外
```java
public void saveProperty() throws IOException
public void saveProperty() throws SQLException
```
改成
```java
public void saveProperty() throws PropertyManipulationException
```
如果saveProperty實作方式改變，不會影響上層呼叫者的例外設計。
- - -
### 2. Homogeneous Exception
```java
public login() throws LoginException
public withdraw() throws WithdrawException
public deposit() throws DepositException
```
改成
```java
public login() throws ATMOperationException
public withdraw() throws ATMOperationException
public deposit() throws ATMOperationException
```
減少過多的例外類別。

### 3. Tunneling Exception
- - -
### 4. Smart Exception
設計列舉類別(enumeration class)來代表錯誤發生的原因。
```java
catch(SQLException e) {
    // 利用error code
    if (e.getErrorCode() == EXTERNAL_ROUTINE_EXCEPTION) {
        compensate();
    }
    throw new AggregationException(e, sql);
}
```
### 5. Exception Hierarchy
- - -
## *例外處理改善原則*
### 1. Replace Error Code with Exception
用回傳值來代表錯誤狀況 => 拋出一個例外
- design fault, 使用 `unckedked exception`
- component fault, 使用 `checked exception`

#### Example
```java
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
        super();
    }
}
```
```java 
pulblic int withdraw(int amount) throws 
                        NotEnoughtMoneyException {
    if (amount > this.balance)
        throw new NotEnoughtMoneyException();
    this.balance = this.balance - amount;
    return this.balance;
}
```
```java
try {
    account.withdraw(amount);
    doNormalLogic();
} catch (NotEnoughtMoneyException e) {
    handleOverdrawn();
}
```
- - -
### 2. Replace Ignored Checked Exception with Unchecked Exception
不知如何處理的checked exception => 轉成自定義的UnhandledException再丟出
#### Example
```java
public class UnhandledException extends RuntimeException {
    public UnhandledException() {
        super();
    }
    public UnhandledException(Throwable e) {
        super(e);
    }
    public UnhandledException(String msg) {
        super(msg);
    }
    public UnhandledException(String msg, Throwable e) {
        super(msg, e);
    }
}
```
```java
pulblic void writeFile(String file, String data) {
    try (Writer wr = new FileWriter(file)) {
        wr.write(data);
    } catch (IOException e){
        throw new UnhandledException(e);
    }
}
```
- - -
### 3. Avoid Unexpected Termination with Big Outer Try Statement
最外層元件以一個try statement包住，用來捕捉所有例外。
#### Example
```java
static public void main(String aargs) {
    try {
        /*
         * 主程式
         */
    } catch (Throwable e){
        /*
         * 顯示錯誤、寫入log
         */
    }
}
```
- - -
### 4. Replace Nested Try Statement with Method
Nested try statement => 抽離到新函數

通常會產生Nested try statement的狀況:
* 在catch block提供替代方案
* 在finally block關閉資源

#### Example
另寫一個cleanup function來關閉資源
```java
private void cleanup(FileInputStream in) {
    try {
        if (in != null) in.close();
    } catch (IOException e){
        // 例外寫入log
    }
}
```
```java
public void readConfig(String file) {
    FileInputStream in = null;
    try {
        in = new FileInputStream(file);
        // normal flow
    } catch (Exception e){
        // 例外
    } finally {
        cleanup(in);
    }
}
```
- - -
### 5. Introduce Checkpoint Class
用checkpoint類別來管理狀態，其具有 `保存狀態`，`回復狀態`，`丟棄狀態`的功能。

#### Example
```java
public class FolderCheckpoint {
    public void establish(String backupFolder) {
        //備份backupFolder的檔案
    }
    public void restore() {
        //備份資料復原
    }
    public void drop() {
        //刪除備份資料
    }
}
```
```java
private void moveFile(String src, String dest) throws IOException {
    FolderCheckpoint cp = new FolderCheckpoint();
    try {
        cp.establish(src);
        // copy src folder to dest folder
    } catch (Exception e){
        cp.restore();
        throw e;
    } finally {
        cp.drop();
    }
}
```
- - -
### 6. Introduce Resourceful Try Block
Retry機制的實作方式

#### Example
```java
public User readUser(String name) throws ReadUserException {
    final int maxAttempt = 3;

    int attempt = 1;
    while(true) {
        try {
            if (attemp <= 2) { // 最多執行兩次
                return readFromDatabase(name);
            } else {
                return readFromLDAP(name);
            }
        } catch (Exception e) {
            if (++attempt > maxAttempt)
                throw new ReadUserException(e);
        }
    }
}
```
- - -
### 7. One method, one try statement
如果函式太多try statement，表示做太多事，應拆開。
