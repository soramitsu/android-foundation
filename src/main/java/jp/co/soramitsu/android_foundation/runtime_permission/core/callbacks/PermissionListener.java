package jp.co.soramitsu.android_foundation.runtime_permission.core.callbacks;

import jp.co.soramitsu.android_foundation.runtime_permission.core.PermissionResult;

import java.util.List;

public interface PermissionListener {
    void onAccepted(PermissionResult permissionResult, List<String> accepted);
    void onDenied(PermissionResult permissionResult, List<String> denied, List<String> foreverDenied);
}
