@file:JvmName("FcmUtils")
package org.thoughtcrime.securesms.notifications

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import kotlinx.coroutines.*


fun getFcmInstanceId(body: (Task<InstanceIdResult>)->Unit): Job = MainScope().launch(Dispatchers.IO) {
    val task = FirebaseInstanceId.getInstance().instanceId
    Tasks.await(task)
    if (!isActive) return@launch // don't 'complete' task if we were canceled
    body(task)
}
