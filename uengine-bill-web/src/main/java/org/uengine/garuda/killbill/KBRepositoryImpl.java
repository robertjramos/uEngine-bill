/**
 * Copyright (C) 2011 uEngine Project (http://www.uengine.io).
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.uengine.garuda.killbill;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.uengine.garuda.common.repository.PersistentRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Seungpil PARK
 */
@Repository
public class KBRepositoryImpl extends PersistentRepositoryImpl<String, Object> implements KBRepository {

    @Override
    public String getNamespace() {
        return this.NAMESPACE;
    }

    @Autowired
    public KBRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public Long subscriptionCountByTenantId(String id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".subscriptionCountByTenantId", id);
    }

    @Override
    public Map getAccountById(String id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".getAccountById", id);
    }

    @Override
    public List<Map> getAccountByIds(List<String> ids) {
        Map map = new HashMap();
        map.put("ids", ids);
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".getAccountByIds", map);
    }

    @Override
    public int updateAccountBcd(String id, int billing_cycle_day_local) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("billing_cycle_day_local", billing_cycle_day_local);
        return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateAccountBcd", map);
    }

    @Override
    public Map getTenantById(String id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".getTenantById", id);
    }

    @Override
    public int deleteAccountById(String id) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteAccountById", id);
    }

    @Override
    public Long getBundleCountByAccountId(String id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".getBundleCountByAccountRecordId", id);
    }

    @Override
    public Long getPaymentCountByAccountId(String id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".getPaymentCountByAccountRecordId", id);
    }
}