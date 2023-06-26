package jp.co.soramitsu.android_foundation.runtime_permission.core.callbacks;

import jp.co.soramitsu.android_foundation.runtime_permission.core.PermissionResult;

public interface ForeverDeniedCallback {
    void onForeverDenied(PermissionResult result);
}
