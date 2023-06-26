package jp.co.soramitsu.android_foundation.runtime_permission.core.callbacks;

import jp.co.soramitsu.android_foundation.runtime_permission.core.PermissionResult;

public interface DeniedCallback {
    void onDenied(PermissionResult result);
}
