package jp.co.soramitsu.android_foundation.runtime_permission.core.callbacks;

import jp.co.soramitsu.android_foundation.runtime_permission.core.PermissionResult;

public interface AcceptedCallback {
    void onAccepted(PermissionResult result);
}
