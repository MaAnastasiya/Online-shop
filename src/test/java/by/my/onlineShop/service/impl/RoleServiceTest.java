package by.my.onlineShop.service.impl;

import by.my.onlineShop.entity.Role;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.RoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleServiceTest {
    private final RoleService roleService;

    RoleServiceTest(){
        roleService = Mockito.mock(RoleServiceImpl.class);
    }

    @Test
    public void testRetrieveRoleById_ShouldReturnTrue_WhenDataIsCorrect() throws ServiceException {
        Optional <Role> role = Optional.of(new Role(1, "user"));
        Mockito.when(roleService.retrieveRoleById(1)).thenReturn(role);
        Optional<Role> actual = roleService.retrieveRoleById(1);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testRetrieveRoleById_ShouldReturnFalse_WhenDataIsNotCorrect() throws ServiceException {
        Optional <Role> role = Optional.empty();
        Mockito.when(roleService.retrieveRoleById(10)).thenReturn(role);
        Optional<Role> actual = roleService.retrieveRoleById(10);
        assertFalse(actual.isPresent());
    }

}
