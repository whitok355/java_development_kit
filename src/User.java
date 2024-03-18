public class User {
    private String nickname;
    public String getNickname() {
        return nickname;
    }
    public String getPassword() {
        return password;
    }
    private String password;
    public User(String nickname, String password){
        this.nickname = nickname;
        this. password = password;
    }
}
