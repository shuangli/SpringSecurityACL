/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util.webflow;

import org.springframework.webflow.execution.*;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.EndState;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.collections.map.ListOrderedMap;

import java.util.*;


public class ProgressFlowExecutionListener extends FlowExecutionListenerAdapter {

    private static Log log = LogFactory.getLog(ProgressFlowExecutionListener.class);

    public static final String PROGRESS_INDEX_VAR_NAME = "stateNumber";
    public static final String PROGRESS_MAX_LENGTH_VAR_NAME = "stateTotal";
    public static final String PROGRESS_INFORMATION_MAP_VAR_NAME = "progressMap";

    private int maxLen = 0;

    private void visit(StateDefinition state, Flow flow, List<StateDefinition> visitedStates, int counter) {

        if(state instanceof EndState) {
            counter++;
            state.getAttributes().put(PROGRESS_INDEX_VAR_NAME, counter);
            if(maxLen < counter)
                maxLen = counter;
            return;
        }

        visitedStates.add(state);
        
        if(state.isViewState()) {
            counter++;
            state.getAttributes().put(PROGRESS_INDEX_VAR_NAME, counter);
        }

        for (TransitionDefinition transition: flow.getTransitionableState(state.getId()).getTransitions()) {
            StateDefinition nextState = flow.getState(transition.getTargetStateId());
            if(visitedStates.contains(nextState))       
                continue;
            visit(nextState, flow, visitedStates, counter);
        }
    }

    @Override
    public void sessionCreating(RequestContext context, FlowDefinition definition) {        
        List<StateDefinition> visitedStates = new ArrayList<StateDefinition>();

        int counter = 0;
        
        Flow flow = (Flow)definition;
        StateDefinition startState = flow.getStartState();
        visit(startState, flow, visitedStates, counter);
    }

    @Override
    @SuppressWarnings(value = "unchecked")    
    public void sessionStarting(RequestContext context, FlowSession session, MutableAttributeMap input) {
         //create or get progressMap from conversational scope
        Map<String, ProgressInfo> progressMap = (Map<String, ProgressInfo>)context.getConversationScope().get(PROGRESS_INFORMATION_MAP_VAR_NAME);
        if(progressMap == null) {
            progressMap = new ListOrderedMap();
        }

        progressMap.put(session.getDefinition().getId(), new ProgressInfo(session.getDefinition().getId()));
        context.getConversationScope().put(PROGRESS_INFORMATION_MAP_VAR_NAME, progressMap);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void viewRendering(RequestContext context, View view, StateDefinition viewState) {

        String currentFlow = viewState.getOwner().getId();

        Map<String, ProgressInfo> progressMap = (Map<String, ProgressInfo>)context.getConversationScope().get(PROGRESS_INFORMATION_MAP_VAR_NAME);
        ProgressInfo progress = progressMap.get(currentFlow);

        if(progress == null) {
            progress = new ProgressInfo(currentFlow);
            progressMap.put(currentFlow, progress);
        } else {
            ListOrderedMap orderedMap = (ListOrderedMap)progressMap;
            String lastFlow = orderedMap.lastKey().toString();

            if(!lastFlow.equalsIgnoreCase(currentFlow))
                ((ListOrderedMap)progressMap).remove(progressMap.size() - 1);
        }

        int index = Integer.parseInt(viewState.getAttributes().get(PROGRESS_INDEX_VAR_NAME).toString());
        progress.setStateNumber(index);
        progress.setStateTotal(maxLen);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void stateEntered(RequestContext context, StateDefinition previousState, StateDefinition newState) {

        if(newState instanceof EndState) {
            String currentFlow = newState.getOwner().getId();
            Map<String, ProgressInfo> progressMap = (Map<String, ProgressInfo>)context.getConversationScope().get(PROGRESS_INFORMATION_MAP_VAR_NAME);
            ProgressInfo progress = progressMap.get(currentFlow);
            int index = Integer.parseInt(newState.getAttributes().get(PROGRESS_INDEX_VAR_NAME).toString());

            progress.setStateNumber(index);
            progress.setStateTotal(maxLen);            
        }
    }
}
