/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.iven.mydemo;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by iven on 2017/6/19.
 */
@Singleton
public class Navigator {


    @Inject
    public Navigator() {
        //empty
    }


    public void navigateToMainActivity(Context context) {
        if (context != null) {
            Intent intent = MainActivity.getMainActivityIntent(context);
            context.startActivity(intent);
        }
    }
}
