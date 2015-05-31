/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.chat.channel.api;


/**
 * A target used to push events to a client.
 * <p>
 * Instance of this interface are usually obtained by an IPushService.
 * <p>
 * This interface extends IChannelTarget, and thus provides
 * methods familiar to {@link org.apache.wicket.ajax.AjaxRequestTarget} users.
 * <p>
 * The specificities of IPushTarget is that you have to call
 *  #trigger() excplictly when you want the events to actually be send
 * to the client. Until the #trigger() method is called, events are 
 * simply queued.
 * <p>
 * The #isConnected() method allows to check if the target is still connected
 * to the client. It is up to the user to call this method before calling
 * any method asking for clients updates.
 * 
 * @author Xavier Hanin
 */
public interface IPushTarget extends IChannelTarget {
	
	void trigger();
	
	boolean isConnected();
}
