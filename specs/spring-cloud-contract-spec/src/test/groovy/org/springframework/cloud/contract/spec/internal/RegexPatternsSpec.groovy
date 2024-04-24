/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.contract.spec.internal

import spock.lang.Specification

class RegexPatternsSpec extends Specification {

	def "should generate a regex for ip address [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.ipAddress().matcher(textToMatch).matches()
		where:
			textToMatch       || shouldMatch
			true
			'a.b.'
	}

	def "should generate a regex for hostname [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.hostname().matcher(textToMatch).matches()
		where:
			textToMatch              || shouldMatch
			true
			true
			true
			true
			'https://asd.com/asd'
			'asd.com'
	}

	def "should generate a regex for email [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.email().matcher(textToMatch).matches()
		where:
			textToMatch        || shouldMatch
			true
			'a.b.'
			true
	}

	// @see https://formvalidation.io/validators/uri/
	def "should generate a regex for url [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.url().matcher(textToMatch).matches()
		where:
			textToMatch                                         || shouldMatch
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			'a.b.'
			'http://'
			'http://.'
			'http://..'
			'https://../'
			'http://?'
			'http://??'
			'https://??/'
			'http://#'
			'http://##'
			'http://##/'
			'https://foo.bar?q=Spaces should be encoded'
			'//'
			'//a'
			'///a'
			'///'
			'https:///a'
			'rdar://1234'
			'h://test'
			'http:// shouldfail.com'
			':// should fail'
			'https://foo.bar/foo(bar)baz quux'
			'https://-error-.invalid/'
			'https://-a.b.co'
			'https://a.b-.co'
			'https://1.1.1.1.1'
			'https://123.123.123'
			'https://3628126748'
			'https://.www.foo.bar/'
			'https://www.foo.bar./'
			'https://.www.foo.bar./'
	}

	def "should generate a regex for httpsUrl [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.httpsUrl().matcher(textToMatch).matches()
		where:
			textToMatch                                         || shouldMatch
			'ftp://asd.com:9090/asd/a?a=b'
			true
			true
			'http://www.foo.com/blah_blah'
			'http://www.foo.com/blah_blah/'
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			'ftp://foo.bar/baz'
			true
			true
			true
			true
			'foo.com'
			'a.b.'
			'https://'
			'https://.'
			'https://..'
			'https://../'
			'https://?'
			'https://??'
			'https://??/'
			'https://#'
			'https://##'
			'https://##/'
			'https://foo.bar?q=Spaces should be encoded'
			'//'
			'//a'
			'///a'
			'///'
			'https:///a'
			'rdar://1234'
			'h://test'
			'https:// shouldfail.com'
			':// should fail'
			'https://foo.bar/foo(bar)baz quux'
			'https://-error-.invalid/'
			'https://-a.b.co'
			'https://a.b-.co'
			'https://1.1.1.1.1'
			'https://123.123.123'
			'https://3628126748'
			'https://.www.foo.bar/'
			'https://www.foo.bar./'
			'https://.www.foo.bar./'
	}

	def "should generate a regex for a number [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.number().matcher(textToMatch).matches()
		where:
			textToMatch || shouldMatch
			true
			true
			true
			true
			'1.'
	}

	def "should generate a regex for a positive integer [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.positiveInt().matcher(textToMatch).matches()
		where:
			textToMatch || shouldMatch
			true
			true
			'-1'
			'0'
			'1.0'
	}

	def "should generate a regex for a double [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.aDouble().matcher(textToMatch).matches()
		where:
			textToMatch || shouldMatch
			'1'
			true
			true
			true
			'1.'
	}

	def "should generate a regex for a uuid [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
		shouldMatch == RegexPatterns.uuid().matcher(textToMatch).matches()
		where:
		textToMatch                           || shouldMatch
		true
		true
		true
		UUID.randomUUID().toString() + "!"
		'23e4567-z89b-12z3-j456-426655440000'
		'dog'
		'5'
	}

	def "should generate a regex for a uuidv4 [#textToMatch] that is a match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.uuid4().matcher(textToMatch).matches()
		where:
			textToMatch                           || shouldMatch
			true
			true
			true
			true
			true
			true
			true
			true
			'00000000-0000-4000-1000-000000000000'
			UUID.randomUUID().toString() + "!"
			'00000000-0000-0000-0000-000000000000'
			'dog'
			'5'
	}

	def "should generate a regex with date [#textToMatch] in YYYY-MM-DD format that should match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.isoDate().matcher(textToMatch).matches()
		where:
			textToMatch  || shouldMatch
			true
			true
			"1014-3-01"
			"14-03-01"
			true
			true
			"1014-12-1"
			"1014-12-32"
			"1014-13-31"
			"1014-20-30"
			'5'
	}

	def "should generate a regex with datetime [#textToMatch] in YYYY-MM-DDTHH:mm:ss format that should match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.isoDateTime().matcher(textToMatch).matches()
		where:
			textToMatch           || shouldMatch
			true
			true
			"1014-3-01T01:01:01"
			true
			"1014-03-01T00:00:0"
			"1014-03-01T00:0:01"
			"1014-03-01T0:01:01"
			"1014-03-0100:01:01"
			"14-03-01T12:23:45"
			true
			true
			"1014-12-1T12:23:45"
			"1014-12-32T12:23:45"
			"1014-13-31T12:23:45"
			"1014-20-30T12:23:45"
			"1014-20-30T24:23:45"
			"1014-20-30T23:60:45"
			"1014-20-30T23:59:60"
	}

	def "should generate a regex with time [#textToMatch] in HH:mm:ss format that should match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.isoTime().matcher(textToMatch).matches()
		where:
			textToMatch || shouldMatch
			true
			true
			true
			"00:00:0"
			"00:0:01"
			"0:01:01"
			"24:23:45"
			"23:60:45"
			"23:59:60"
	}

	def "should generate a regex with iso8601DateTimeWithTimezone [#textToMatch] in YYYY-MM-DDTHH:mm:ss.SSSZZ format that should match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.iso8601WithOffset().matcher(textToMatch).matches()
		where:
			textToMatch                        || shouldMatch
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			true
			'2014-03-01T12:23:45'
			'2014-03-01T12:23:45.123'
	}

	def "should generate a regex for a non blank string [#textToMatch] that should match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.nonBlank().matcher(textToMatch).matches()
		where:
			textToMatch    || shouldMatch
			true
			''
			'    '
			true
			'\r\n'
			' \r\n\t'

	}

	def "should generate a regex for a non empty string [#textToMatch] that should match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.nonEmpty().matcher(textToMatch).matches()
		where:
			textToMatch    || shouldMatch
			true
			''
			true
			true
			true
			true

	}

	def "should generate a regex for an enumerated value [#textToMatch] that should match [#shouldMatch]"() {
		expect:
			shouldMatch == RegexPatterns.anyOf('foo', 'bar').matcher(textToMatch).matches()
		where:
			textToMatch || shouldMatch
			true
			true
			'baz'
	}
}
