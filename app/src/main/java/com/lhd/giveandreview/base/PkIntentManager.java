package com.lhd.giveandreview.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;

import com.lhd.Constants;
import com.lhd.util.SmartLog;

import java.util.ArrayList;
import java.util.HashMap;


public class PkIntentManager {

    public static String TAG = PkIntentManager.class.getSimpleName().toString();

    private static PkIntentManager sInstance;

    private Handler mHandler;

    /**
     * Comment : EXTRA KEY 값은 PkIntentManager에 공통으로 관리 합니다.
     */

    //공통
    public static final String EXTRA_TEXT = "TEXT";        // 일반 String
    public static final String EXTRA_TEXT_LIST = "TEXT_LIST";    // String ArrayList
    public static final String EXTRA_NUMBER = "NUMBER";
    public static final String EXTRA_BOOLEAN = "BOOLEAN";
    public static final String EXTRA_WINGMENU = "WINGMENU";
    public static final String EXTRA_IMAGE = "IMAGE";        // ImageData
    public static final String EXTRA_IMAGE_LIST = "IMAGE_LIST";    // ImageDatas
    public static final String EXTRA_OBJECT = "OBJECT";        // some object
    public static final String EXTRA_OBJECT_LIST = "OBJECT_LIST";// some objects
    public static final String EXTRA_TITLE = "EXTRA_TITLE";    // Acitity Title
    public static final String EXTRA_NO_INTENT_ANIM = "NO_INTENT_ANIM";
    public static final String EXTRA_GENDER = "GENDER";
    public static final String EXTRA_TYPE = "TYPE";
    public static final String EXTRA_SORT_TYPE = "SORT_TYPE";
    public static final String EXTRA_TYPE_SEARCH = "TYPE_SEARCH";
    public static final String EXTRA_TYPE_IMG = "TYPE_IMG";
    public static final String EXTRA_TO_MAIN = "EXTRA_TO_MAIN";         // 회원가입 시 메인페이지로 보내는 여부
    public static final String EXTRA_BIRTHDAY = "EXTRA_BIRTHDAY";
    public static final String EXTRA_ADDRESS = "ADDRESS";    // 전체 주소
    public static final String EXTRA_ADDRESS_TOWN = "EXTRA_ADDRESS_TOWN";    // 번지수 제외한 주소
    public static final String EXTRA_ADDRESS_HOUSE_NUMB = "EXTRA_ADDRESS_HOUSE_NUMB";    // 번지수
    public static final String EXTRA_URL = "EXTRA_URL";
    public static final String EXTRA_CURSOR = "EXTRA_CURSOR";
    public static final String EXTRA_FOR_PUT_USER_SNS = "EXTRA_FOR_PUT_USER_SNS";
    public static final String EXTRA_CONNECT = "EXTRA_CONNECT";
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TAB_POSITION = "EXTRA_TAB_POSITION";
    public static final String EXTRA_POPUP = "EXTRA_POPUP";
    public static final String EXTRA_IS_PARKING = "EXTRA_IS_PARKING";

    //카테 고리
    public static final String EXTRA_CATE_ID = "CATE_ID";    // CategoryData (int)
    public static final String EXTRA_CATE_NAME = "CATE_NAME";    // CategoryData (int)
    public static final String EXTRA_TYPE_CATEGORY = "TYPE_CATEGORY";
    public static final String EXTRA_INTERESTED_CATEGORY = "INTERESTED_CATEGORY";
    public static final String EXTRA_CATE_REFRSH = "CATE_REFRESH";

    //로그인
    public static final String EXTRA_SEND_FOR_LOGIN = "SEND_FOR_LOGIN";
    public static final String EXTRA_OWP_ID = "OWP_ID";

    //PICK 하기
    public static final String EXTRA_PICK_ID = "PICK_ID";    // PickData (long)
    public static final String EXTRA_PICK_DATA = "PICK_INFO";            // PoiData
    public static final String EXTRA_REPLY_ID = "REPLY_ID";
    public static final String EXTRA_IS_FIRST_PICK = "EXTRA_IS_FIRST_PICK";
    public static final String EXTRA_IS_ADD_IMG = "EXTRA_IS_ADD_IMG";

    //USER
    public static final String EXTRA_USER_ID = "USER_ID";    // user id (long)
    public static final String EXTRA_TYPE_USERS = "TYPE_USERS";
    public static final String EXTRA_IS_PICKER = "IS_PICKER";
    public static final String EXTRA_USER_DATA = "POI_INFO";            // PoiData
    public static final String EXTRA_MIDDLE_USER_LOGIN = "EXTRA_MIDDLE_USER_LOGIN";
    public static final String EXTRA_NON_MEMBER_USER_LOGIN = "EXTRA_NON_MEMBER_USER_LOGIN";
    public static final String EXTRA_NON_SYRUP_USER_LOGIN = "EXTRA_NON_SYRUP_USER_LOGIN";

    //POI
    public static final String EXTRA_POI_ID_TYPE = "POI_ID_TYPE";        // Coupon에서 poiid type (pickat, nate)
    public static final String EXTRA_POI_ID = "POI_ID";                // poi id (long)
    public static final String EXTRA_POI_DATA = "POI_INFO";            // PoiData
    public static final String EXTRA_ARRARY_POI = "ARRARY_POI";            // POI들의 정보
    public static final String EXTRA_BLOG_RATINGS = "EXTRA_BLOG_RATINGS";    // 블로그 평가점수 테이블
    public static final String EXTRA_SUGGESTIONS = "SUGGESTIONS_POI";    // 메인 주변 POI suggestion
    public static final String EXTRA_IS_SYRUP_ORDER = "IS_SYRUP_ORDER";        // 시럽오더 여부


    //쿠폰
    public static final String EXTRA_COUPON_LIST = "EXTRA_COUPON_LIST";    // Coupon	List
    public static final String EXTRA_COUPON_CODE = "COUPON_CODE";        // Coupon code
    public static final String EXTRA_COUPON_SRC = "COUPON_SRC";            // Coupon	출처
    public static final String EXTRA_COUPON_PSN = "COUPON_PSN";            // Coupon 발급번호
    public static final String EXTRA_IS_COUPON = "IS_COUPON";            // 검색 시 Coupon 선택 했는지 여부
    public static final String EXTRA_COUPON_AVILABLE_AOIS = "EXTRA_COUPON_AVILABLE_AOIS"; // 이용 가능한 aoi 목록
    public static final String EXTRA_PIN_TYPE = "PIN_TYPE";            // Coupon Pin type

    //AOI 위치 지역 TMAP
    public static final String EXTRA_AROUND_AOI_LIST = "EXTRA_AROUND_AOI_LIST";        // Coupon	List
    public static final String EXTRA_LAT = "LAT";            // latitude
    public static final String EXTRA_LNG = "LNG";            // longuitude
    public static final String EXTRA_REGION_ID = "REGION_ID";        // 검색 시 Coupon 선택 했는지 여부
    public static final String EXTRA_MAP_TYPE = "MAP_TYPE";    // // Map관련 Activity로 이동시 Map Type 결정
    public static final String EXTRA_AOI_ID = "EXTRA_AOI_ID";
    public static final String EXTRA_AOI_NAME = "EXTRA_AOI_NAME";
    public static final String EXTRA_PICKABLE = "EXTRA_PICKABLE"; // OneMarkerMap 에서 바로 Pick하기 연결가능
    public static final String EXTRA_GPS = "EXTRA_GPS";

    //SNS
    public static final String EXTRA_SNS_USER_INFO = "EXTRA_SNS_USER_INFO";     // SNS USER INFO
    public static final String EXTRA_SNS_TOKEN = "EXTRA_ACCESS_TOKEN";     // SNS TOKEN
    public static final String EXTRA_SNS_SECRET = "EXTRA_SNS_SCRET";     // SNS SCRET
    public static final String EXTRA_TYPE_SNS = "EXTRA_TYPE_SNS";
    public static final String EXTRA_BITLYURL = "EXTRA_BITLYURL";

    public static final String EXTRA_SNS_SHARE_TYPE = "EXTRA_SNS_SHARE_TYPE";
    public static final String EXTRA_SNS_SHARE_ID = "EXTRA_SNS_SHARE_ID";

    public static final String EXTRA_SNS_SHARE_IMG = "EXTRA_SNS_SHARE_IMG";
    public static final String EXTRA_SNS_SHARE_DEFAULT_TEXT = "EXTRA_SNS_SHARE_DEFAULT_TEXT";
    public static final String EXTRA_SNS_SHARE_TXT1 = "EXTRA_SNS_SHARE_TXT1";
    public static final String EXTRA_SNS_SHARE_TXT2 = "EXTRA_SNS_SHARE_TXT2";
    public static final String EXTRA_SNS_SHARE_TXT3 = "EXTRA_SNS_SHARE_TXT3";


    //키워드
    public static final String EXTRA_KEYWORD = "KEYWORD";
    public static final String EXTRA_KEYWORD_RELATED_WORD = "EXTRA_KEYWORD_RELATED_WORD";
    public static final String EXTRA_KEYWORD_ID = "KEYWORD_ID";


    //테마
    public static final String EXTRA_THEME_ID = "EXTRA_THEME_ID";
    public static final String EXTRA_THEME_TYPE_BEFORE = "EXTRA_THEME_TYPE_BEFORE";
    public static final String EXTRA_THEME_TYPE = "EXTRA_THEME_TYPE";
    public static final String EXTRA_THEME_TITLE = "EXTRA_THEME_TITLE";
    public static final String EXTRA_THEME_WHOLE_LAND = "EXTRA_THEME_WHOLE_LAND";
    public static final String EXTRA_KEYWORD_WHOLE_LAND = "EXTRA_KEYWORD_WHOLE_LAND";
    public static final String EXTRA_THEME_LIST = "EXTRA_THEME_LIST";

    // 이미지 상세
    public static final String EXTRA_IMAGE_TYPE = "EXTRA_IMAGE_TYPE";
    public static final String EXTRA_IMAGE_POSITION = "EXTRA_IMAGE_POSITION";
    public static final String EXTRA_IMAGE_TOTAL_COUNT = "EXTRA_IMAGE_TOTAL_COUNT";
    public static final String EXTRA_IMAGE_DATA = "EXTRA_IMAGE_DATA";

    // Bi log
    public static final String EXTRA_FROM_PAGE_CODE = "EXTRA_FROM_PAGE_CODE";
    public static final String EXTRA_FROM_ACTION_CODE = "EXTRA_FROM_ACTION_CODE";
    public static final String EXTRA_FROM_DISP_ORDER = "EXTRA_FROM_DISP_ORDER";

    /**
     * Comment : Request code 값은 PkIntentManager에 공통으로 관리 합니다.
     */
    public static final int REQ_CODE_GALLERY = 10; // 갤러리 호출
    public static final int REQ_CODE_TEXT_INPUT = 20; // 리뷰, 테마, 인사말 작성
    public static final int REQ_CODE_IMAGE_LOAD = 21; // 이미지 불러오기 공통
    public static final int REQ_CODE_CAMERA = 22; // 카메라
    public static final int REQ_CODE_PREVIEW = 23; // 이미지 미리보기(크롭, 필터 포함)
    public static final int REQ_CODE_FIND_ADDRESS = 24; // 동명/도로명으로 찾기
    public static final int REQ_CODE_LIST_CATEGORY = 25; // 카테고리 리스트(업종 선택)
    public static final int REQ_CODE_MAP_ACTIVITY = 26; // Map 진입 후 현재 위치값 변경 시 필요
    public static final int REQ_CODE_AOI_LIST = 27; // Aoi list에서 선택후 적용 부분
    public static final int REQ_CODE_DO_PICK = 28; // Pick하기 페이지 호출
    public static final int REQ_CODE_THEME_SELECT = 29; // 테마선택(검색 및 생성)
    public static final int REQ_CODE_ACCESS_TOKEN = 30; // access token
    public static final int REQ_CODE_GENDER = 31; // 성별
    public static final int REQ_CODE_USER_INFO = 32; // 유저 정보
    public static final int REQ_CODE_USER_EDIT = 33; // 유저 정보 수정
    public static final int REQ_CODE_SELECT_CATE = 34; // 관심카테고리 선택
    public static final int REQ_CODE_DELETE_SEACH_WORD = 35; // 검색어 삭제
    public static final int REQ_CODE_REFRESH = 36; // 데이터 업데이트
    public static final int REQ_CODE_GPS_CHECK = 37; // gps 체크
    public static final int REQ_CODE_SNS_MIGRATION_FAIL = 38; // 회원 통합 실패
    public static final int REQ_CODE_GPS = 39; // GPS
    public static final int REQ_CODE_TERMS_AGREE = 40; // 약관동의
    public static final int REQ_CODE_MAIN = 41; // 메인으로 이동

    private Activity mLastActivity;
    private ArrayList<ClassMap> mClassArray = new ArrayList<ClassMap>();
    private ArrayList<ExtraParam> mExtras = new ArrayList<ExtraParam>();

    private boolean mBlock = false; // 에니메이션 중 인텐트 요청 방지
    private int mIntentAniTime = 400;

    public static PkIntentManager getInstance() {
        if (sInstance == null) {
            sInstance = new PkIntentManager();
        }
        return sInstance;
    }

    public PkIntentManager() {

        mHandler = new Handler();
    }

    /**
     * Comment : EXTRAS 데이터를 연속적으로 사용할 경우 초기화 시키지 않는다.
     */
    private void init() {

        mExtras.clear();
    }

    private void log(String tag) {

        if (Constants.CRASH_REPORT_LOGGER == false) {

            SmartLog.getInstance().w(TAG, tag + "PkIntentManager log size " + mClassArray.size());

            int size = mClassArray.size();
            for (int i = 0; i < size; i++) {

                SmartLog.getInstance().w(TAG, tag + "PkIntentManager log " + mClassArray.get(i).simpleName);
            }
        }
    }

    public String getForeGroundActivityName() {


        if (mClassArray != null
                && mClassArray.isEmpty() == false) {

            return mClassArray.get(mClassArray.size() - 1).simpleName;

        }

        return null;

    }

    /**
     * Comment : Intent startActivity
     *
     * @param initExtraData Extras 초기화
     */
    public void push(final Activity currentAct, Class<?> cls, boolean initExtraData) {

        if (mBlock) {

            SmartLog.getInstance().d(TAG, "push() is block:");
            return;
        }


        final Intent intent = set(currentAct, cls, initExtraData);
        log("push");


        currentAct.startActivity(intent);
//        currentAct.overridePendingTransition(
//                R.anim.pk_start_enter, R.anim.pk_start_exit);

    }


    public void push(final Activity currentAct, Class<?> cls, boolean initExtraData, int flags, final boolean transition) {

        if (mBlock) {

            SmartLog.getInstance().d(TAG, "pushAddFlags() is block:");
            return;
        }

        final Intent intent = set(currentAct, cls, initExtraData);
        log("push");

        if (flags > 0) {

            intent.addFlags(flags);

        }


        currentAct.startActivity(intent);

        if (transition) {

//            currentAct.overridePendingTransition(
//                    R.anim.pk_start_enter, R.anim.pk_start_exit);

        } else {

            currentAct.overridePendingTransition(0, 0);

        }


    }


    /**
     * Comment : Intent startActivityForResult
     *
     * @param initExtraData Extras 초기화
     */
    public void pushForResult(final Activity currentAct, Class<?> cls, final int requestCode, boolean initExtraData, final boolean transition, int flags) {

        if (mBlock) {

            SmartLog.getInstance().d(TAG, "pushForResult() is block:");
            return;
        }

        final Intent intent = set(currentAct, cls, initExtraData);

        log("pushForResult");

        if (flags > 0) {

            intent.addFlags(flags);
        }


        currentAct.startActivityForResult(intent, requestCode);

        if (transition) {

//            currentAct.overridePendingTransition(
//                    R.anim.pk_start_enter, R.anim.pk_start_exit);

        } else {

            currentAct.overridePendingTransition(0, 0);

        }

    }


    public void pushForResult(final Activity currentAct, Class<?> cls, final int requestCode, boolean initExtraData, int flags, final int anim_enter, final int anim_exit) {

        if (mBlock) {

            SmartLog.getInstance().d(TAG, "pushForResult() is block:");
            return;
        }

        final Intent intent = set(currentAct, cls, initExtraData);

        log("pushForResult");

        if (flags > 0) {

            intent.addFlags(flags);
        }


        currentAct.startActivityForResult(intent, requestCode);
        currentAct.overridePendingTransition(
                anim_enter, anim_exit);

    }

    /**
     * @param currentAct
     * @param phoneNumber : 전화번호(String)
     * @Method Name : doCallToNumber
     * @author mangosteen@kiwiple.com
     * @date : 2014. 1. 28.
     * Desc : 전화걸기
     */
    public void doCallToNumber(Activity currentAct, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        currentAct.startActivity(intent);
    }

    /**
     * @param currenrAct
     * @param url        : web url (String)
     * @Method Name : doLinkWebUrl
     * @author mangosteen@kiwiple.com
     * @date : 2014. 1. 28.
     * Desc : 외부 브라우저로 연결
     */
    public void doLinkWebUrl(Activity currenrAct, String url) {

        StringBuffer sb = new StringBuffer();
        if (url != null && url.length() > 0) {

            if (url.contains("https://") || url.contains("http://")) {
                sb.append(url);
            } else {
                sb.append("http://").append(url);
            }

            String checkedUrl = sb.toString();


            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(checkedUrl));
            currenrAct.startActivity(intent);
        }
    }

    /**
     * @param currentAct
     * @param action
     * @param requestCode
     * @Method Name : pushWithActionForResult
     * @author mangosteen@kiwiple.com
     * @date : 2014. 1. 22.
     * Desc : 사진촬영과 같은 Action Value를 이용한 Intent 실행
     */
    public void pushWithActionForResult(Activity currentAct, String action,
                                        int requestCode) {

        if (mBlock) {

            SmartLog.getInstance().d(TAG, "pushWithActionForResult() is block:");
            return;
        }


        Intent intent = set(currentAct, action);
        log("pushWithActionForResult");
        currentAct.startActivityForResult(intent, requestCode);

//        currentAct.overridePendingTransition(
//                R.anim.pk_start_enter, R.anim.pk_start_exit);

    }

    private Intent set(Activity currentAct, Class<?> cls, boolean initExtraData) {

        mBlock = true;
        SmartLog.getInstance().d(TAG, "set() : mBlock:" + mBlock);

        SmartLog.getInstance().d(TAG, "from : " + currentAct.toString());
        SmartLog.getInstance().d(TAG, "to : " + cls.getSimpleName());

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                mBlock = false;
                SmartLog.getInstance().d(TAG, "set(activity) delay : mBlock:" + mBlock);
            }
        }, mIntentAniTime);

        mClassArray.add(new ClassMap(cls.getSimpleName().toString(), cls));

        Intent intent = new Intent(currentAct, cls);

        SmartLog.getInstance().d(TAG, "set() intent or action : " + cls.getSimpleName().toString());

        if (mExtras.size() > 0) {
            for (ExtraParam param : mExtras) {

                SmartLog.getInstance().d(TAG, "ExtraParam name:" + param.name + ", value:" + param.value);
                param.put(intent);
            }
        } else {
            SmartLog.getInstance().d(TAG, "NoExtra ");
        }

        if (initExtraData) {

            init();
        }

        return intent;
    }

    /**
     * @param currentAct
     * @param action
     * @return
     * @Method Name : set
     * @author mangosteen@kiwiple.com
     * @date : 2014. 1. 22.
     * Desc : Action String을 이용한 Intent 실행
     */

    private Intent set(Activity currentAct, String action) {

        mBlock = true;
        SmartLog.getInstance().d(TAG, "set(action) : mBlock:" + mBlock);

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                mBlock = false;
                SmartLog.getInstance().d(TAG, "set(action) delay : isBlock:" + mBlock);
            }
        }, mIntentAniTime);

        mClassArray.add(new ClassMap(action, null));

        Intent intent = new Intent(action);

        if (mExtras.size() > 0) {
            for (ExtraParam param : mExtras) {

                SmartLog.getInstance().w(TAG,
                        "PkIntentManager ExtraParam " + param.name + ":" + param.value);
                param.put(intent);
            }
        }

        init();

        return intent;
    }

    /**
     * @param name
     * @param value
     * @Method Name : putExtra
     * @author mangosteen@kiwiple.com
     * @date : 2014. 1. 21.
     * Desc : 일반 Extra Data
     */
    public void putExtra(String name, Object value) {

        mExtras.add(new ExtraParam(name, value, false));
    }

    /**
     * @param name
     * @param value
     * @Method Name : putExtraParcelable
     * @author mangosteen@kiwiple.com
     * @date : 2014. 1. 21.
     * Desc : Parcelable Extra data 만 사용
     */
    public void putExtraParcelable(String name, Object value) {

        mExtras.add(new ExtraParam(name, value, true));
    }

    /**
     * Comment : 기본 activity finish
     */
    public void pop(final Activity currentAct) {

        if (mClassArray.size() > 0) {

            mClassArray.remove(mClassArray.size() - 1);
        }
        log("pop");


        currentAct.finish();
//        currentAct.overridePendingTransition(
//                R.anim.pk_end_enter, R.anim.pk_end_exit);

    }

    public void pop(final Activity currentAct, final int anim_enter, final int anim_exit) {

        if (mClassArray.size() > 0) {

            mClassArray.remove(mClassArray.size() - 1);
        }
        log("pop");


        currentAct.finish();
        currentAct.overridePendingTransition(
                anim_enter, anim_exit);

    }

    /**
     * Comment : for No Animation activity finish
     */
    public void popForNoAnim(Activity act) {

        if (mClassArray.size() > 0) {

            mClassArray.remove(mClassArray.size() - 1);
        }
        log("pop");

        if (act != null) {
            act.finish();
        }

        // 단말기 기본 Animaion 으로 사용하기 위해 (0, 0) 주석 처리
        //		act.overridePendingTransition(0, 0);
    }


    public void popForFake(Activity act) {

        if (mClassArray.size() > 0) {

            mClassArray.remove(mClassArray.size() - 1);
        }
        log("pop");

        act.finish();
        act.overridePendingTransition(0, 0);
    }

    /**
     * Comment : 특정 index activity finish
     */
    public void pop(Activity act, int index) {

        if (mClassArray.size() > 0) {

            mClassArray.remove(mClassArray.size() - index);

        }
        act.finish();
    }

    /**
     * Comment : 특정 클래스 remove()
     */
    protected void remove(Class<?> targetCls) {

        int size = mClassArray.size();
        for (int i = 0; i < size; i++) {

            if (targetCls.getSimpleName().toString()
                    .equalsIgnoreCase(mClassArray.get(i).simpleName)) {

                mClassArray.remove(i);

            }

        }

    }

    protected class ClassMap {
        protected String simpleName;
        protected Class<?> className;

        public ClassMap(String tagName, Class<?> cls) {
            simpleName = tagName;
            className = cls;
        }
    }

    ;

    public class ExtraParam {
        String name;
        Object value;
        boolean isParcelable = false;

        public ExtraParam(String n, Object v, boolean isParcelable) {
            name = n;
            value = v;
            this.isParcelable = isParcelable;
        }

        public void put(Intent i) {

            if (isParcelable == true) {

                if (value instanceof ArrayList<?>) {

                    i.putExtra(name, (ArrayList<?>) (value));
                } else {

                    i.putExtra(name, (Parcelable) (value));
                }
            } else {
                if (value instanceof String) {

                    i.putExtra(name, (String) value);
                } else if (value instanceof Integer) {

                    i.putExtra(name, (Integer) (value));
                } else if (value instanceof Long) {

                    i.putExtra(name, (Long) (value));
                } else if (value instanceof Float) {

                    i.putExtra(name, (Float) (value));
                } else if (value instanceof Double) {

                    i.putExtra(name, (Double) (value));
                } else if (value instanceof Boolean) {

                    i.putExtra(name, (Boolean) (value));
                } else if (value instanceof Character) {

                    i.putExtra(name, (Character) (value));
                } else if (value instanceof Uri) {

                    i.putExtra(name, (Uri) (value));
                } else if (value instanceof String[]) {

                    i.putExtra(name, (String[]) (value));
                } else if (value instanceof ArrayList<?>) {

                    i.putExtra(name, (ArrayList<?>) (value));
                } else if (value instanceof HashMap<?, ?>) {

                    i.putExtra(name, (HashMap<?, ?>) (value));
                }
            }
        }
    }

    public boolean isFirstActivity() {

        if (mClassArray != null && mClassArray.size() == 0) {
            return true;
        }
        return false;
    }


    /**
     * @Method Name : setBlockCancel
     * @author mangosteen@kiwiple.com
     * @date : 2014. 5. 7.
     * Desc : 중복호출을 막기위해 사용한 mBlock을 임시로 해제할때 사용한다. (투명엑티비티 이용시에만 사용)
     */
    public void setBlockCancelForTransparentActivity() {

        mBlock = false;
        SmartLog.getInstance().d(TAG, "setBlockCancelForTransparentActivity() : mBlock:" + mBlock);
    }

}
