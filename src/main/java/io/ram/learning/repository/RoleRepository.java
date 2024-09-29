package io.ram.learning.repository;

import java.util.Collection;

import io.ram.learning.domain.Role;

public interface RoleRepository<T extends Role>  {
	/* Basic CRUD Operations */
	T create(T data);

	Collection<T> list(int page, int pageSize);

	T get(Long id);

	T update(T data);

	Boolean delete(long id);
	
	/* More Complex Operations */
	
	void addRoleToUser(long userId, String roleName);
	Role getRoleByUserId(long userId);
	Role getRoleByUserEmail(String email);
	void updateUserRole(long userId, String roleName);
}
