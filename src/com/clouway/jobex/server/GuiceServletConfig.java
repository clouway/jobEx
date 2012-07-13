package com.clouway.jobex.server;

import com.clouway.jobex.server.job.JobRepository;
import com.clouway.jobex.server.job.JobRepositoryImpl;
import com.clouway.jobex.server.job.jobsearch.JobSearchServiceImpl;
import com.clouway.jobex.server.job.jobsearch.JobSearchService;
import com.clouway.jobex.inject.MyConstraintValidatorFactory;
import com.clouway.jobex.inject.MyRequestFactoryServlet;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {

      @Override
      protected void configureServlets() {
        serve("/gwtRequest").with(MyRequestFactoryServlet.class);

        bind(JobRepository.class).to(JobRepositoryImpl.class);
        bind(JobSearchService.class).to(JobSearchServiceImpl.class);
      }

      @Provides
      @Singleton
      ValidatorFactory getValidatorFactory(Injector injector) {
        return Validation.byDefaultProvider().configure().constraintValidatorFactory(new MyConstraintValidatorFactory(injector)).buildValidatorFactory();
      }

      @Provides
      @Singleton
      Validator getValidator(ValidatorFactory validatorFactory) {
        return validatorFactory.getValidator();
      }

      @Provides
      DatastoreService getDatastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
      }
    }


    );
  }
}
