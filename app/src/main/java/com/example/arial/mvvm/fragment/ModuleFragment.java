package com.example.arial.mvvm.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arialyy.frame.module.AbsModule;
import com.example.arial.mvvm.R;
import com.example.arial.mvvm.base.BaseFragment;
import com.example.arial.mvvm.config.Constance;
import com.example.arial.mvvm.databinding.FragmentModuleBinding;
import com.example.arial.mvvm.dialog.IpDialog;
import com.example.arial.mvvm.module.AddressModule;
import com.example.arial.mvvm.module.BindingModule;
import com.example.arial.mvvm.module.IPModule;

import butterknife.Bind;

/**
 * Created by lyy on 2016/7/13.
 */
public class ModuleFragment extends BaseFragment<FragmentModuleBinding>
    implements View.OnClickListener {
  @Bind(R.id.use_module_1) Button mUseModule1;
  @Bind(R.id.use_module_2) Button mUseModule2;
  @Bind(R.id.use_module_3) Button mUseModule3;
  @Bind(R.id.show_dialog) Button mShowDialog;

  public static ModuleFragment newInstance() {
    Bundle args = new Bundle();

    ModuleFragment fragment = new ModuleFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void init(Bundle savedInstanceState) {
    super.init(savedInstanceState);
    mUseModule1.setOnClickListener(this);
    mUseModule2.setOnClickListener(this);
    mUseModule3.setOnClickListener(this);
    mShowDialog.setOnClickListener(this);
  }

  @Override protected int setLayoutId() {
    return R.layout.fragment_module;
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.use_module_1:
        //                getModule(IPModule.class).getIpInfo();
        getModule(IPModule.class, new AbsModule.OnCallback() {
          @Override public void onSuccess(int result, Object success) {

          }

          @Override public void onError(int result, Object error) {

          }
        }).getIpInfo();
        break;
      case R.id.use_module_2:
        getModule(AddressModule.class, new AbsModule.OnCallback() {

          @Override public void onSuccess(int result, Object success) {
            if (result == Constance.KEY.GET_ADDR) {
              getBinding().setStr(success + "");
            }
          }

          @Override public void onError(int key, Object error) {

          }
        }).getAddr();
        break;
      case R.id.use_module_3:
        getModule(BindingModule.class).fragmentBindingTest();
        break;
      case R.id.show_dialog:
        IpDialog dialog = new IpDialog(this);
        dialog.show(getChildFragmentManager(), "ip_dialog");
        break;
    }
  }

  @Override protected void dataCallback(int result, Object obj) {
    super.dataCallback(result, obj);
    if (result == Constance.KEY.GET_IP) {
      getBinding().setStr(obj + "");
    } else if (result == Constance.KEY.IP_DIALOG) {
      getBinding().setDialogStr(obj + "");
    }
  }
}
