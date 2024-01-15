package fi.klab.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fi.klab.demo.ds1.Data1;
import fi.klab.demo.ds1.Repository1;
import fi.klab.demo.ds2.Data2;
import fi.klab.demo.ds2.Repository2;
import jakarta.transaction.Transactional;

@Configuration
//@EnableTransactionManagement
public class Operations {

    @Autowired
	private Repository1 repo1;

    @Autowired
	private Repository2 repo2;

    //@Transactional
	public void addDataToRepo1(String name){
		Data1 data1 = new Data1();
        data1.setName(name);
        
        data1 = repo1.save(data1);
	}

    public void addDataToRepo2(String name){
		Data2 data2 = new Data2();
        data2.setName(name);
        
        data2 = repo2.save(data2);
	}
}
