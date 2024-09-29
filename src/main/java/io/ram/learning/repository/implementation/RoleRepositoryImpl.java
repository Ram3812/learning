package io.ram.learning.repository.implementation;

import java.util.Collection;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import io.ram.learning.domain.Role;
import io.ram.learning.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {
	
	private final NamedParameterJdbcTemplate jdbc;

	@Override
	public Role create(Role data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Role> list(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role update(Role data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRoleToUser(long userId, String roleName) {
		log.info("Adding role {} to user id {}", roleName, userId);
		
	}

	@Override
	public Role getRoleByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getRoleByUserEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserRole(long userId, String roleName) {
		// TODO Auto-generated method stub
		
	}

}
