package sg.edu.np.mad.sguardian;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private String userId;

    private UserType userType;
    private String parentUserID;
    private String username;
    private String passwordHash;
    private String email;
    private String otherProfileInfo;
    private Date createdAt;
    private Date updatedAt;

    public enum UserType {
        Adult,
        CHILD
    }

    // Getters, setters, constructors
    public String getUserId() {
        return userId;
    }
}
