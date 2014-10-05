package com.hannesdorfmann.annotatedadapter.processor.util;

import com.hannesdorfmann.annotatedadapter.processor.AnnotatedAdapterProcessor;
import com.hannesdorfmann.annotatedadapter.processor.ViewTypeSearcher;
import dagger.Module;
import dagger.Provides;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.inject.Singleton;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author Hannes Dorfmann
 */
@Module(
    injects = {
        AnnotatedAdapterProcessor.class, ViewTypeSearcher.class, ProcessorMessage.class
    }

    , library = true // TODO remove
)
public class AnnotatedAdapterModule {

  private ProcessingEnvironment environment;

  public AnnotatedAdapterModule(ProcessingEnvironment environment) {
    this.environment = environment;
  }

  @Provides
  public Types providesTypes() {
    return environment.getTypeUtils();
  }

  @Provides
  public Elements providesElements() {
    return environment.getElementUtils();
  }

  @Singleton @Provides
  public ViewTypeSearcher provideViewTypeSeracher() {
    return new ViewTypeSearcher();
  }

  @Singleton @Provides
  public ProcessorMessage provideProcessorMessage() {
    return new ProcessorMessage(environment.getMessager());
  }

  @Provides
  public Filer providesFiler() {
    return environment.getFiler();
  }
}
