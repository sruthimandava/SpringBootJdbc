package com.tek.springbootjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tek.springbootjdbc.model.Post;

@Repository
public class PostRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PostRepository(JdbcTemplate jdbc){
		this.jdbcTemplate = jdbc;
	}

	public Post findFirstByOrderByPostedOnDesc() {
		String sql = "select * from post order by posted_on limit 1";
		return jdbcTemplate.queryForObject(
				sql,
				new RowMapper<Post>() {
					public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Post post = new Post();
		                post.setId( rs.getInt("id") );
		    			post.setTitle( rs.getString("title") );
		    			post.setTeaser( rs.getString("teaser") );
		    			post.setBody( rs.getString("body") );
		    			post.setSlug( rs.getString("slug") );
		    			post.setPostedOn( rs.getDate("posted_on") );
		    			post.setAuthor( rs.getInt("author_id") );
		                return post;
		            }
				}
		);
		
		
//		BeanPropertyRowMapper<Post> beanPropertyRowMapper = new BeanPropertyRowMapper<Post>(Post.class);
//		return jdbcTemplate.queryForObject(sql, beanPropertyRowMapper);
		
		
	}

	public List<Post> findAllByOrderByPostedOnDesc() {
		String sql = "select * from post order by posted_on";
		return jdbcTemplate.query(sql, (rs,i) -> new Post(
			rs.getInt("id"),
			rs.getString("title"),
			rs.getString("teaser"),
			rs.getString("body"),
			rs.getString("slug"),
			rs.getDate("posted_on"),
			rs.getInt("author_id")
		));
	}

	public Post findBySlug(String slug) {
		String sql = "select * from post where slug = ? order by posted_on";
		return jdbcTemplate.queryForObject(
				sql,
				new Object[]{slug},
				new RowMapper<Post>() {
					public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Post post = new Post();
		                post.setId( rs.getInt("id") );
		    			post.setTitle( rs.getString("title") );
		    			post.setTeaser( rs.getString("teaser") );
		    			post.setBody( rs.getString("body") );
		    			post.setSlug( rs.getString("slug") );
		    			post.setPostedOn( rs.getDate("posted_on") );
		    			post.setAuthor( rs.getInt("author_id") );
		                return post;
		            }
				}
		);	
		
		// BeanPropertyRowMapper<Post> beanPropertyRowMapper = new
		// BeanPropertyRowMapper<Post>(Post.class);
		// return jdbcTemplate.queryForObject(sql, beanPropertyRowMapper, slug);
	}
	
	
	
}