package com.clouway.jobex.client.cv;

import com.clouway.jobex.client.applyingforjob.RequestFactoryHelper;
import com.clouway.jobex.client.security.UsernameProvider;
import com.clouway.jobex.server.cv.CV;
import com.clouway.jobex.server.cv.CvsService;
import com.clouway.jobex.shared.CVProxy;
import com.clouway.jobex.shared.JobExRequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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
public class EditCvWorkflowTest {


  private JobExRequestFactory factory;

  private CvsService service;

  private JobExRequestFactory.CVsRequestContext context;

  private EditCvWorkflow workflow;

  @Mock
  private UsernameProvider provider;

  @Mock
  private EditCVWorkflowView view;

  private String username = "mail@mail.com";

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    factory = RequestFactoryHelper.create(JobExRequestFactory.class);

    service = RequestFactoryHelper.getService(CvsService.class);

    context = factory.cvsRequestContext();

    workflow = new EditCvWorkflow(factory, view, provider);

  }

  @Test
  public void fetchCvProxyTobeEdited() {

    CV cv = new CV(1l, "name", username, "1234567", "skill", new Date(), "male");

    ArgumentCaptor<CVProxy> cvProxyArgumentCaptor = ArgumentCaptor.forClass(CVProxy.class);

    when(service.fetchCreatedCv(username, 1l)).thenReturn(cv);

    when(provider.getUsername()).thenReturn(username);

    workflow.editCv(1l);

    verify(service).fetchCreatedCv(username, 1l);

    verify(view).edit(cvProxyArgumentCaptor.capture(), isA(JobExRequestFactory.CVsRequestContext.class));

    CVProxy editedCv = cvProxyArgumentCaptor.getValue();

    assertThat(editedCv, is(notNullValue()));

    assertThat(editedCv.getId(), is(equalTo(1l)));

    assertThat(editedCv.getName(), is(equalTo("name")));

    assertThat(editedCv.getPhoneNumber(), is(equalTo("1234567")));

    assertThat(editedCv.getSkills(), is(equalTo("skill")));

  }


  @Test
  public void savesEditedProxy() {

    CVProxy proxy = context.create(CVProxy.class);

    proxy.setId(1l);
    proxy.setName("name");
    proxy.setPhoneNumber("1234567");
    proxy.setSkills("nothing ... !");

    when(provider.getUsername()).thenReturn(username);

    workflow.update(proxy, context);

    ArgumentCaptor<CV> cvArgumentCaptor = ArgumentCaptor.forClass(CV.class);

    verify(service).add(eq(username), cvArgumentCaptor.capture());

    CV cv = cvArgumentCaptor.getValue();

    assertThat(cv.getId(), is(equalTo(1l)));
    assertThat(cv.getEmail(), is(equalTo("mail@mail.com")));
    assertThat(cv.getName(), is(equalTo("name")));
    assertThat(cv.getPhoneNumber(), is(equalTo("1234567")));
    assertThat(cv.getSkills(), is(equalTo("nothing ... !")));
    verify(view).goToCvPreview();

  }


}
