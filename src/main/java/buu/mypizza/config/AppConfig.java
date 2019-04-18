/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buu.mypizza.config;

import buu.mypizza.dao.DAO;
import buu.mypizza.dao.ProductDAO;
import buu.mypizza.dao.UserDAO;
import buu.mypizza.mappers.Mapper;
import buu.mypizza.mappers.ProductDTOMapper;
import buu.mypizza.mappers.ProductMapper;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author nazar
 */

@Configuration
@ComponentScan(basePackages = "buu.mypizza")
public class AppConfig {
}
