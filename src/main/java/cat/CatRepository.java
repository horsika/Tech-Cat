package cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CatRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public Cat findCat(int id) {
        SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM cats WHERE id = " + id);
        Cat cat = new Cat();
        while (rs.next()) {
            cat.setId(rs.getInt("id"));
            cat.setBreed(rs.getString("breed"));
            cat.setColor(rs.getString("color"));
        }
        return cat;
    }

    public List<Cat> findAllCats() {
        SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM cats");
        List<Cat> catList = new ArrayList<>();
        while (rs.next()) {
            Cat cat = new Cat();
            cat.setId(rs.getInt("id"));
            cat.setBreed(rs.getString("breed"));
            cat.setColor(rs.getString("color"));

            catList.add(cat);
        }
        return catList;
    }

    public Cat insert(Cat cat) {

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO cats VALUES(null , ?,?)", new String[]{"id"});
                ps.setString(1, cat.getBreed());
                ps.setString(2, cat.getColor());
                return ps;
            }
        };

        KeyHolder id = new GeneratedKeyHolder();
        jdbc.update(psc, id);
        cat.setId(id.getKey().intValue());
        return cat;
    }


    public void delete(int id) {
        jdbc.update("DELETE FROM cats WHERE id = " + id);
    }

    public void update(Cat cat) {
        jdbc.update("UPDATE cats SET " +
                "breed='" + cat.getBreed() + "', " +
                "color='" + cat.getColor() + "' " +
                "WHERE id=" + cat.getId());
    }
}
