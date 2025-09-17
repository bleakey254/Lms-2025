// src/adapter/ResultAdapter.java
package adapter;

import model.Result;

import java.util.List;

public interface ResultAdapter {
    List<Result> getResultsForUser(String userId);
}

