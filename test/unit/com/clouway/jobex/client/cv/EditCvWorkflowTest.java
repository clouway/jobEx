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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class EditCvWorkflowTest {


  private JobExRequestFactory factory;
  private CvsService service;
  private EditCvWorkflow workflow;

  @Mock
  private UserCredentialsLocalStorage provider;

  @Mock
  private EditCVWorkflowView view;

  @Captor
  private ArgumentCaptor<JobExRequestFactory.CVsRequestContext> requestContextCaptor;

  @Captor
  private ArgumentCaptor<String> usernameCaptor;

  @Captor
  private ArgumentCaptor<Long> cvIdCaptor;

  @Captor
  private ArgumentCaptor<CVProxy> cvProxyCaptor;

  @Captor
  private ArgumentCaptor<CV> cvCaptor;

  @Captor
  private ArgumentCaptor<List<String>> violations;

  private final Long cvId = 12l;
  private final String username = "Ivan";
  private CV cv;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    service = RequestFactoryHelper.getService(CvsService.class);

    workflow = new EditCvWorkflow(factory, view, provider);

    cv = new CV();
  }

  @Test
  public void getCVForEditing() {

    setCVForEditing();

    workflow.editCv(cvId);

    verify(provider).getUsername();
    verify(service).fetchCreatedCv(usernameCaptor.capture(), cvIdCaptor.capture());
    verify(view).edit(cvProxyCaptor.capture(), requestContextCaptor.capture());

    assertThat(cvId, is(equalTo(cvIdCaptor.getValue())));
    assertThat(username, is(equalTo(usernameCaptor.getValue())));
  }

  @Test
  public void saveEditedCV() {

    setCVForEditing();

    cv.setDateOfBirth(new Date());
    cv.setName("name");
    cv.setPhoneNumber("0884669080");
    cv.setSkills("skills");

    workflow.editCv(cvId);
    workflow.saveEditedCV();

    verify(service).add(usernameCaptor.capture(), cvCaptor.capture());
    verify(view, never()).showConstraintViolations(violations.capture());
    verify(view).reset();
    verify(view).goToCvPreview();
  }

  private void setCVForEditing() {

    when(provider.getUsername()).thenReturn(username);
    when(service.fetchCreatedCv(username, cvId)).thenReturn(cv);
  }
}
