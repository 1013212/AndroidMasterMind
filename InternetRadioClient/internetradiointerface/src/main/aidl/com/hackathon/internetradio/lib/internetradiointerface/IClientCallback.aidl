

package com.hackathon.internetradio.lib.internetradiointerface;

import com.hackathon.internetradio.lib.internetradiointerface.IClientListener;
import com.hackathon.internetradio.lib.internetradiointerface.IClientInterface;

interface IClientCallback {

        /**
         * @brief Register sync connection
         */
        IClientInterface getSyncConnection();

        /**
         * @brief Register async connection
         */
        void registerAsyncConnection(IClientListener clientListener);

        /**
         * @brief Un register async connection
         */
        void unRegisterAsyncConnection(IClientListener clientListener);
}
