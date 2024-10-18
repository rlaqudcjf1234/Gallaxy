package frame;

import java.util.ArrayList;

class User {
    private String id;
    private String pw;
    private String name;
    private String nickName;
    private String gender;
    private String email;

    public User(String id, String pw, String name, String nickName, String gender, String email) {
        setId(id);
        setPw(pw);
        setName(name);
        setNickName(nickName);
        setGender(gender);
        setEmail(email);
    }
    public User(String id) {
        setId(id);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    
    @Override
    public boolean equals(Object e) {
        if(e == null || !(e instanceof User)) {
            return false;
        }
        User temp = (User) e;

        return id.equals(temp.getId());
    }

    @Override
    public String toString() {
        String info = "Id: " + id + "\n";
        info += "Pw: " + pw + "\n";
        info += "Name: " + name + "\n";
        info += "NickName: " + nickName + "\n";
        info += "gender: " + gender + "\n";
        info += "E-mail: " + email + "\n";
        return info;
    }
}

public class LoginUserDataSet {
	private ArrayList<User> users;
	
	public LoginUserDataSet() {
		users = new ArrayList<User>();
	}
	
	// 회원 추가
	public void addUsers(User user) {
	        users.add(user);
	}
	// 아이디 중복 확인
    public boolean isIdOverlap(String id) {
    	return users.contains(new User(id));
    }
    // 회원 삭제
	public void withdraw(String id) {
       users.remove(getUser(id));
    }
	// 유저 정보 가져오기
	public User getUser(String id) {
		return users.get(users.indexOf(new User(id)));
	}
	// 회원인지 아닌지 확인
	public boolean contains(User user) {
		return users.contains(user);
	}

}