package dev.agasen.cargo.booking.application.common;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * T - aggregate type
 * U - command type
 */
public abstract class BaseCommandHandler<T, U> {

  @Autowired
  protected Repository<T> repository;

  @CommandHandler
  public void doHandle(U command) throws Exception {
    T aggregate = handle(command);
    repository.newInstance(() -> aggregate);
  }

  public abstract T handle(U command);

}