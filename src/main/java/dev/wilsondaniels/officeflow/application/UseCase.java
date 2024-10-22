package dev.wilsondaniels.officeflow.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN in);
}