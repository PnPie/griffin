/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package org.apache.griffin.measure.sink

import scala.collection.mutable

import org.apache.spark.rdd.RDD

/**
  * sink records and metrics in memory for test.
  *
  * @param config sink configurations
  * @param metricName
  * @param timeStamp
  * @param block
  */
case class CustomSink(config: Map[String, Any],
                      metricName: String,
                      timeStamp: Long,
                      block: Boolean) extends Sink {
  def available(): Boolean = true

  def start(msg: String): Unit = {}

  def finish(): Unit = {}

  def log(rt: Long, msg: String): Unit = {}

  val allRecords = mutable.ListBuffer[String]()

  def sinkRecords(records: RDD[String], name: String): Unit = {
    allRecords ++= records.collect()
  }

  def sinkRecords(records: Iterable[String], name: String): Unit = {
    allRecords ++= records
  }

  val allMetrics = mutable.Map[String, Any]()

  def sinkMetrics(metrics: Map[String, Any]): Unit = {
    allMetrics ++= metrics
  }
}
