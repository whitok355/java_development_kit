package service;

public class User {
    private int id;
    private String nickname;
    private String password;
    private Boolean active;
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public int getId() {
        return id;
    }
    public String getNickname() {
        return nickname;
    }
    public String getPassword() {
        return password;
    }
    public User(int id, String nickname, String password, Boolean active){
        this.id= id;
        this.nickname = nickname;
        this. password = password;
        this.active = active;
    }
}
