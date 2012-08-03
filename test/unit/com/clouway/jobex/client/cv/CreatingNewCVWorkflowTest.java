package com.clouway.jobex.client.cv;

import com.clouway.jobex.RequestFactoryHelper;
import com.clouway.jobex.client.security.UserCredentialsLocalStorage;
import com.clouway.jobex.server.cv.CV;
import com.clouway.jobex.server.cv.CvsService;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCVWorkflowTest {


  private JobExRequestFactory factory;

  private CvsService service;

  private CreatingNewCVWorkflow workflow;

  @Mock
  private UserCredentialsLocalStorage provider;

  @Mock
  private CreatingNewCVWorkflowView view;


  private String username = "user";

  @Captor
  ArgumentCaptor<JobExRequestFactory.CVsRequestContext> requestContextCaptor;

  @Captor
  ArgumentCaptor<CVProxy> cvProxyCaptor;

  @Captor
  ArgumentCaptor<String> usernameCaptor;

  @Captor
  ArgumentCaptor<CV> cvCaptor;

  @Captor
  ArgumentCaptor<List<String>> violations;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    service = RequestFactoryHelper.getService(CvsService.class);

    workflow = new CreatingNewCVWorkflow(view, factory, provider);

  }

  @Test
  public void prepareNewCV() {

    when(service.prepareNewCV()).thenReturn(new CV());

    workflow.prepareCV();

    verify(service).prepareNewCV();
    verify(view).edit(requestContextCaptor.capture(), cvProxyCaptor.capture());
  }

  @Test
  public void createNewCV() {

    CV cv = new CV();

    when(service.prepareNewCV()).thenReturn(cv);
    when(provider.getUsername()).thenReturn(username);

    cv.setName("myName");
    cv.setDateOfBirth(new Date());
    cv.setPhoneNumber("0888123456");
    cv.setSkills("skills");

    workflow.prepareCV();
    workflow.createCV();

    verify(provider).getUsername();
    verify(view).flush();
    verify(service).add(usernameCaptor.capture(), cvCaptor.capture());
    verify(view, never()).showConstraintViolations(violations.capture());
    verify(view).reset();
    verify(view).goToSelectCv();

    assertThat(username, is(equalTo(usernameCaptor.getValue())));
    assertThat(cv.getName(), is(equalTo(cvCaptor.getValue().getName())));
    assertThat(cv.getPhoneNumber(), is(equalTo(cvCaptor.getValue().getPhoneNumber())));
  }
}
