package ridebooking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIR = "data";
    private static final String USER_FILE = DATA_DIR + File.separator + "users.dat";
    private String userId;
    private String name;
    private String email;
    private String phone;

    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    // Save user to file
    public void saveToFile() {
        List<User> users = getAllUsers();
        users.add(this);
        try {
            File dir = new File(DATA_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
                oos.writeObject(users);
                System.out.println("User saved to file: " + name);
            }
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    // Get all users from file
    @SuppressWarnings("unchecked")
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USER_FILE);
        
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
                users = (List<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error reading users: " + e.getMessage());
            }
        }
        return users;
    }

    // Find user by ID
    public static User findById(String userId) {
        return getAllUsers().stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
} 