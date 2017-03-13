package com.nttdata.tdb.services.user.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.nttdata.oat.dao.repo.role.RoleRepo;
//import com.nttdata.oat.domain.role.RoleDomain;

import com.nttdata.tdb.services.user.RoleServices;


/**
 * @author hudson.brito
 *
 */
@Service
public class RoleServicesImpl implements RoleServices {
	
	private static final Logger LOG = Logger.getLogger(RoleServicesImpl.class
	        .getName());

//    @Autowired
//    private RoleRepo roleRepo;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.nttdata.mdg.role.services.MdgRoleServices#existRole(java.lang.String)
     
    public boolean existRole(String roleDesc) throws Exception {
    	LOG.log(Level.INFO, "Executing RoleServicesImpl - existRole method");
    	
        RoleDomain roleDomain = roleRepo.findByRoleDescription(roleDesc);

        if (roleDomain != null) {
            return true;
        }

        return false;
    }


    /* (non-Javadoc)
     * @see com.nttdata.oat.services.auth.RoleServices#getRole(java.lang.String)
     
    @Override
    public RoleDomain getRole(String roleDesc) throws Exception {
    	LOG.log(Level.INFO, "Executing RoleServicesImpl - getRole method");
        return roleRepo.findByRoleDescription(roleDesc);
    }


    /* (non-Javadoc)
     * @see com.nttdata.oat.services.auth.RoleServices#roleById(java.lang.Long)
     
	@Override
	public RoleDomain roleById(Long id) {
		LOG.log(Level.INFO,"Executing RoleServicesImpl - roleById method");
		return roleRepo.findOne(id);
	}
*/



}
