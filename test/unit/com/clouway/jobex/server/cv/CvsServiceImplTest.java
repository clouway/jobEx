package com.clouway.jobex.server.cv;

import com.clouway.jobex.shared.entities.CV;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CvsServiceImplTest {


  @Mock
  CVRepository cvRepository;

  CvsServiceImpl service;
  private String username="user";

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    service = new CvsServiceImpl(cvRepository);

  }

  @Test
  public void returnsCreatedCvs() {

    List<CV> cvs = new ArrayList<CV>();

    cvs.add(new CV());

    cvs.add(new CV());

    when(cvRepository.getCreatedCVs(username)).thenReturn(cvs);

    List<CV> returnedList = service.fetchCreatedCVs(username);

    verify(cvRepository).getCreatedCVs(username);

    assertThat(returnedList, is(notNullValue()));

    assertThat(returnedList.size(), is(2));
  }

}
