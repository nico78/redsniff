/*******************************************************************************
 * Copyright 2014 JHC Systems Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package jhc.redsniff.webdriver.activity;

import jhc.redsniff.webdriver.factory.WebDriverFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Duration;

public abstract class AjaxActivity implements Activity {

    @Override
    public boolean busyOn(WebDriver driver) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        Boolean ajaxBusy = (Boolean) jsExec
                .executeScript(ajaxBusyExpr());
        return ajaxBusy;
    }

    @Override
    public Duration initialDelay() {
        return WebDriverFactory.getInstance().getInitialAjaxDelayForDriver();
    }

    protected abstract String ajaxBusyExpr();

}
