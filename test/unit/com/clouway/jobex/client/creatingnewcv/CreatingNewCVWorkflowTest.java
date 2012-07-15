package com.clouway.jobex.client.creatingnewcv;

import com.clouway.jobex.client.applyingforjob.RequestFactoryHelper;
<<<<<<< HEAD
import com.clouway.jobex.server.cv.CV;
import com.clouway.jobex.shared.JobExRequestFactory;
import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.server.cv.CvsService;
import com.clouway.jobex.shared.CVProxy;
=======
>>>>>>> commit
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CreatingNewCVWorkflowTest {


  private JobExRequestFactory factory;

  private CvsService service;

  private JobExRequestFactory.CVsRequestContext context;

  private CreatingNewCVWorkflow workflow;

  @Mock
  private UsernameProvider provider;

  @Mock
  private CreatingNewCVWorkflowView view;

  private String username = "user";


  @Before
  public void setUp() throws Exception {

    initMocks(this);

    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    service = RequestFactoryHelper.getService(CvsService.class);

    context = factory.cvsRequestContext();

    workflow = new CreatingNewCVWorkflow(view, factory, provider);

  }

  @Test
  public void shouldInitializeTheDriverWithTheRequestFactory() {

    when(provider.getUsername()).thenReturn(username);

    workflow.initialize();

    verify(view).initializeEditorWithRequestFactory(isA(JobExRequestFactory.class));

    verify(view).edit(isA(JobExRequestFactory.CVsRequestContext.class), isA(CVProxy.class));

  }


  @Test
  public void flushesEditorAndFiresRequestContext() {


    ArgumentCaptor<CV> cvArgumentCaptor = ArgumentCaptor.forClass(CV.class);

    when(view.flush()).thenReturn(context);

    workflow.initialize();

    workflow.create();

    verify(service).create(eq(username), cvArgumentCaptor.capture());

    assertThat(cvArgumentCaptor.getValue(), is(notNullValue()));

    verify(view).notifyUserOfSuccessfulCVCreation();
  }

}
