package com.patika.week6.user.repository;

import com.patika.week6.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(UUID id, User user) {
        final String sql = "" +
                "INSERT INTO user (" +
                " id, " +
                " name) " +
                "VALUES (?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                user.getName()
        );
    }

    @Override
    public List<User> selectAllUser() {
        final String sql = "SELECT id, name FROM user";
        return jdbcTemplate.query(sql, (rs, i) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            return new User(id, name);
        });
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        final String sql = "SELECT id, name FROM user WHERE id = ?";
        User selectedUser = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, i) -> {
                    UUID user = UUID.fromString(rs.getString("id"));
                    String name = rs.getString("name");
                    return new User(user, name);
                }
        );
        return Optional.ofNullable(selectedUser);
    }

    @Override
    public int deleteUserById(UUID id) {
        String sql = "" +
                "DELETE FROM user " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateUserById(UUID id, User user) {
        String sql = "" +
                "UPDATE user" +
                "SET name = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), id);
    }
}
