/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package com.ichi2.utils

//https://github.com/ankidroid/Anki-Android/issues/17073

object StartIntentUtil {
    // Function to start an activity and clear the task stack
    fun startWithNewTaskAndClearStack(context: Context, targetActivity: Class<*>, extras: Bundle? = null) {
        val intent = Intent(context, targetActivity).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            extras?.let { putExtras(it) }
        }
        context.startActivity(intent)
    }

    // Function to start an activity and bring it to the front (clear activities above)
    fun startWithClearTop(context: Context, targetActivity: Class<*>, extras: Bundle? = null) {
        val intent = Intent(context, targetActivity).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            extras?.let { putExtras(it) }
        }
        context.startActivity(intent)
    }

    // Function to start an activity and avoid re-creating it if it's already on top
    fun startWithSingleTop(context: Context, targetActivity: Class<*>, extras: Bundle? = null) {
        val intent = Intent(context, targetActivity).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            extras?.let { putExtras(it) }
        }
        context.startActivity(intent)
    }
}