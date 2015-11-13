/* vim: set expandtab tabstop=4 shiftwidth=4 softtabstop=4 */

/*
 * Copyright 2014 AT&T
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Provides classes for sending RESTFul requests and handling responses.
 *
 * <p>
 * This class follows the dependency inversion principle by applying a varation
 * of the adapter pattern. That is, this class is essentially a wrapper with a
 * simplified interface to a full http client.
 * </p>
 *
 * @author pk9069
 * @since 1.0
 * @see com.att.api.rest.RESTClient
 */

package com.att.api.rest;
