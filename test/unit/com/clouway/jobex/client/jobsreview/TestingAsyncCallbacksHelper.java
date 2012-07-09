package com.clouway.jobex.client.jobsreview;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import static org.mockito.Mockito.doAnswer;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 2:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestingAsyncCallbacksHelper {







  public static <T> Stubber doOnSuccess(final T returnedExpenses) {

    return doAnswer(new Answer<T>() {
      @Override
      public T answer(InvocationOnMock invocation) throws Throwable {

        Object[] args = invocation.getArguments();

        AsyncCallback<T> callback = (AsyncCallback<T>) args[args.length - 1];

        callback.onSuccess(returnedExpenses);

        return null;  //To change body of implemented methods use File | Settings | File Templates.
      }
    });
  }


  public static <T>Stubber doOnFailure(final Throwable throwable) {

    return doAnswer(new Answer<Throwable>() {
            @Override
            public Throwable answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();

                AsyncCallback<T> callback = (AsyncCallback<T>) args[args.length - 1];

                callback.onFailure(throwable);

                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }




}
