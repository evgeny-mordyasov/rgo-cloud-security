package rgo.cloud.security.config.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rgo.cloud.common.api.model.Role;

import java.util.Collection;
import java.util.Collections;

public class ClientDetails implements UserDetails {
    private Client object;

    public ClientDetails(Client object) {
        this.object = object;
    }

    public ClientDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(object.role.name()));
    }

    public Client getObject() {
        return object;
    }

    public void setObject(Client object) {
        this.object = object;
    }

    @Override
    public String getPassword() {
        return object.password;
    }

    @Override
    public String getUsername() {
        return object.mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return object.isVerified;
    }

    @Override
    public boolean isAccountNonLocked() {
        return object.isVerified;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return object.isVerified;
    }

    @Override
    public boolean isEnabled() {
        return object.isVerified;
    }

    public static class Client {
        private Long entityId;
        private String mail;
        private String password;
        private Role role;
        private boolean isVerified;

        public Client(Long entityId, String mail, String password, Role role, boolean isVerified) {
            this.entityId = entityId;
            this.mail = mail;
            this.password = password;
            this.role = role;
            this.isVerified = isVerified;
        }

        public Client() {
        }

        public Long getEntityId() {
            return entityId;
        }

        public void setEntityId(Long entityId) {
            this.entityId = entityId;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public boolean isVerified() {
            return isVerified;
        }

        public void setVerified(boolean verified) {
            isVerified = verified;
        }
    }
}
