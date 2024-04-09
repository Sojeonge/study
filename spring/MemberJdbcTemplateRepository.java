package com.elice.elcademy.member.repository;

import com.elice.elcademy.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJdbcTemplateRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> {
        Member member = new Member();
        member.setMemberId(rs.getLong("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPhone(rs.getString("phone"));

        return member;
    };

    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, memberRowMapper);
    }

    public Optional<Member> findById(Long id) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, memberRowMapper).stream().findFirst();
    }

    public Member save(Member member) {
        if (member.getMemberId() == null) {
            String insertSql = "INSERT INTO member (name, email, phone) VALUES (?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"member_id"});
                        ps.setString(1, member.getName());
                        ps.setString(2, member.getEmail());
                        ps.setString(3, member.getPhone());
                        return ps;
                    }, keyHolder);

            Number key = keyHolder.getKey();
            if (key != null) {
                member.setMemberId(key.longValue());
            }
        } else {
            String updateSql = "UPDATE member SET name = ?, email = ?, phone = ? WHERE member_id = ?";
            jdbcTemplate.update(updateSql, member.getName(), member.getEmail(), member.getPhone(), member.getMemberId());
        }
        return member;
    }

    public void delete(Member member) {
        String sql = "DELETE FROM member WHERE member_id = ?";
        jdbcTemplate.update(sql, member.getMemberId());
    }

}
