package model.response;

public class LoginResponseJava {
    private LoginDataJava data;
    private String message;
    private String timestamp;

    public LoginDataJava getData(){
        return data;
    }

    public void setData(LoginDataJava data){
        this.data=data;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message=message;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(String timestamp){
        this.timestamp=timestamp;
    }
}
