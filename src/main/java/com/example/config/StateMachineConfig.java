package com.example.config;

import com.example.enums.Events;
import com.example.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/22 16:03
 * @Version 1.0
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, Events> {

    private static Logger logger = LoggerFactory.getLogger(StateMachineConfig.class);

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, Events> state) throws Exception {
        state.withStates()
                .initial(OrderStatus.UNPAID)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, Events> transitions) throws Exception {
        transitions.withExternal()
                        .source(OrderStatus.UNPAID).target(OrderStatus.WAITING_FOR_RECEIVE)
                        .event(Events.PAY)
                        .and()
                    .withExternal()
                        .source(OrderStatus.WAITING_FOR_RECEIVE).target(OrderStatus.DONE)
                        .event(Events.RECEIVE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStatus, Events> config) throws Exception {
        config.withConfiguration().listener(listener());
    }

    @Bean
    public StateMachineListener<OrderStatus,Events> listener() {
        return new StateMachineListenerAdapter<OrderStatus, Events>() {
            @Override
            public void transitionEnded(Transition<OrderStatus, Events> transition) {
                if(transition.getTarget().getId() == OrderStatus.UNPAID) {
                    logger.info("订单创建，待支付");
                    return;
                }

                if(transition.getSource().getId() == OrderStatus.UNPAID
                        && transition.getTarget().getId() == OrderStatus.WAITING_FOR_RECEIVE) {
                    logger.info("用户完成支付，待收货");
                    return;
                }

                if(transition.getSource().getId() == OrderStatus.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == OrderStatus.DONE) {
                    logger.info("用户已收货，订单完成");
                    return;
                }
            }
        };
    }
}
