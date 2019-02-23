/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jmeter.assertions

import org.apache.jmeter.assertions.gui.AssertionGui;
import org.apache.jmeter.junit.spock.JMeterSpec
import spock.lang.Unroll

import java.nio.charset.StandardCharsets

@Unroll
class AssertionGuiSpec extends JMeterSpec {

    def sut = new AssertionGui()

    def "init of component fails"() {
        when:
            sut.init()
        then:
            notThrown(Exception)
    }
    
    def "GUI component fields clearing fails"() {
        when:
            sut.clearGui()
        then:
            notThrown(Exception)
    }
    
    def "Creation of ResponseAssertion fails"() {
        when:
            def result = sut.createTestElement()
        then:
            result.getName().equals("Response Assertion")
            result.isEnabled()
    }
    
    def "Modification of ResponseAssertion by GUI has unexpected behaviour"() {
        given:
            def element = new ResponseAssertion();
            sut.clearGui()
        when:
            sut.modifyTestElement(element)
        then:
            element.getName().equals("Response Assertion")
            element.isTestFieldResponseData()
            element.getTestStrings().isEmpty()
            !element.getAssumeSuccess()
            !element.isNotType()
            element.isSubstringType()
    }
    
    def "Modification of GUI by ResponseAssertion has unexpected behaviour"() {
        given:
            def element = new ResponseAssertion();
        when:
            sut.configure(element)
        then:
            notThrown(Exception)
    }
}