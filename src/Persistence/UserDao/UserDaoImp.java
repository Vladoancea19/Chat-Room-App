package Persistence.UserDao;

import Persistence.UserFileManagement.UserFileReader;
import Persistence.UserFileManagement.UserFileWriter;
import User.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserDaoImp implements UserDao {
    private static final File USER_FILE;

    static {
        try {
            USER_FILE = UserFileWriter.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final FileWriter USER_FILE_WRITER = UserFileWriter.getFileWriter();

    private static final BufferedReader BUFFERED_READER;

    static {
        try {
            BUFFERED_READER = UserFileReader.getBufferedReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User create(User userEntity) {
        try {
            if(USER_FILE.length() == 0) {
                USER_FILE_WRITER.append("username, password\n");
                USER_FILE_WRITER.append(userEntity.getUsername());
                USER_FILE_WRITER.append(",");
                USER_FILE_WRITER.append(userEntity.getPassword());
                USER_FILE_WRITER.append("\n");
            }
            else {
                USER_FILE_WRITER.append(userEntity.getUsername());
                USER_FILE_WRITER.append(",");
                USER_FILE_WRITER.append(userEntity.getPassword());
                USER_FILE_WRITER.append("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                assert USER_FILE_WRITER != null;
                USER_FILE_WRITER.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userEntity;
    }

    @Override
    public List<User> read() {
        List<User> userEntityList = new ArrayList<>();

        try {
            BUFFERED_READER.readLine();

            String line;
            while((line = BUFFERED_READER.readLine()) != null) {
                String[] column = line.split(",");
                if(column.length > 0) {
                    User userEntity = new User();
                    userEntity.setUsername(column[0]);
                    userEntity.setPassword(column[1]);
                    userEntityList.add(userEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntityList;
    }

    @Override
    public Optional<User> update(User entity, Integer integer) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer integer) {
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllBy(Predicate<User> predicate) {
        return null;
    }
}
