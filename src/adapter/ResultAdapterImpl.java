// src/adapter/ResultAdapterImpl.java
package adapter;

import model.Result;
import service.ResultService;

import java.util.List;

public class ResultAdapterImpl implements ResultAdapter {

    private final ResultService resultService;

    public ResultAdapterImpl() {
        this.resultService = new ResultService();
    }

    @Override
    public List<Result> getResultsForUser(String userId) {
        return resultService.getResultsByUserId(Integer.parseInt(userId));
    }
}
