/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.repository;

import java.util.List;
import mvc.domain.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author yasuaki
 */
@Mapper
public interface SampleMapper {

    public List<Customer> findAll();

}
