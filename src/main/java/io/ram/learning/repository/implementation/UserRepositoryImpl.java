package io.ram.learning.repository.implementation;

import static io.ram.learning.enumarations.RoleType.*;
import static io.ram.learning.enumarations.VerificationType.*;
import static io.ram.learning.query.UserQuery.*;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.ram.learning.domain.Role;
import io.ram.learning.domain.User;
import io.ram.learning.exception.ApiException;
import io.ram.learning.repository.RoleRepository;
import io.ram.learning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

	private final NamedParameterJdbcTemplate jdbc;
	private final RoleRepository<Role> roleRepository;
	private final BCryptPasswordEncoder encoder;

	@Override
	public User create(User user) {
		// Check the email is unique
		if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0)
			throw new ApiException("Email is already in use. Please use a different email");
		// Save new user
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			SqlParameterSource parameters = getSqlParameterSource(user);
			jdbc.update(INSER_USER_QUERY, parameters, holder);
			user.setId(requireNonNull(holder.getKey()).longValue());
			// Add a role to the user
			roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());
			// Send verification URL
			String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
			// Save URL in verification table
			jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY, of("userId", user.getId(), "url", verificationUrl));
			// Send email to user with verification URL
			// emailService.sendVerificationUrl(user.getFirstName(), user.getEmail(), verificationUrl, ACCOUNT);
			user.setEnabled(false);
			user.setNotLocked(true);
			// Return the newly created user
			return user;
			// If any errors, throw exception with proper message
		} catch (EmptyResultDataAccessException exception) {
			throw new ApiException("No role found by name " + ROLE_USER.name());
		} catch (Exception exception) {
			throw new ApiException("Something went wromg, it's not you it's Us. Please retry.");
		}
	}

	@Override
	public Collection<User> list(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getEmailCount(String email) {
		return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, of("email", email), Integer.class);
	}

	private SqlParameterSource getSqlParameterSource(User user) {
		return new MapSqlParameterSource().addValue("firstName", user.getFirstName())
				.addValue("lastName", user.getLastName()).addValue("email", user.getEmail())
				.addValue("password", encoder.encode(user.getPassword()));
	}

	private String getVerificationUrl(String key, String type) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "/" + key)
				.toUriString();
	}
}
