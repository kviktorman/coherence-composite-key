```
<?xml version="1.0"?>
<cache-config xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xmlns="http://xmlns.oracle.com/coherence/coherence-cache-config"
              xsi:schemaLocation="http://xmlns.oracle.com/coherence/coherence-cache-config coherence-cache-config.xsd">
  <caching-scheme-mapping>
    <cache-mapping>
        <cache-name>*</cache-name>
        <scheme-name>near-direct</scheme-name>
    </cache-mapping>
  </caching-scheme-mapping>
  <caching-schemes>

    <!-- Proxy schema that starts a service where the extend clients can join -->
    <proxy-scheme>
        <service-name>Proxy</service-name>
        <acceptor-config>
          <tcp-acceptor>
            <local-address>
              <address system-property="coherence.extend.address">0.0.0.0</address>
              <port system-property="coherence.extend.port">7574</port>
            </local-address>
          </tcp-acceptor>
        </acceptor-config>
        <load-balancer>client</load-balancer>
        <autostart>true</autostart>
    </proxy-scheme>

    <!-- remote caching scheme for accessing the proxy from extend clients -->
    <remote-cache-scheme>
      <scheme-name>thin-remote</scheme-name>
      <service-name>RemoteCache</service-name>
      <proxy-service-name>Proxy</proxy-service-name>
    </remote-cache-scheme>


    <!-- near caching scheme for extend clients -->
    <near-scheme>
      <scheme-name>near-remote</scheme-name>
      <scheme-ref>near-direct</scheme-ref>
      <back-scheme>
        <remote-cache-scheme>
          <scheme-ref>thin-remote</scheme-ref>
        </remote-cache-scheme>
      </back-scheme>
    </near-scheme>


    <!-- near caching scheme for clustered clients -->
    <near-scheme>
      <scheme-name>near-direct</scheme-name>
      <front-scheme>
        <local-scheme>
          <high-units>{front-limit-entries 10000}</high-units>
        </local-scheme>
      </front-scheme>
      <back-scheme>
        <distributed-scheme>
          <scheme-ref>thin-direct</scheme-ref>
	    </distributed-scheme>
      </back-scheme>
    </near-scheme>

    <!-- partitioned caching scheme for clustered clients -->
    <distributed-scheme>
      <scheme-name>thin-direct</scheme-name>
      <scheme-ref>distributed-coherence-cache</scheme-ref>
      <local-storage system-property="coherence.distributed.localstorage">false</local-storage>
      <autostart>false</autostart>
    </distributed-scheme>

    <!-- shared cache storage among all cache servers -->
    <distributed-scheme>
         <scheme-name>distributed-coherence-cache</scheme-name>
         <service-name>DistributedCacheStorage</service-name>
         <backing-map-scheme>
            <local-scheme>
              <high-units>{back-tier-high-units 0}</high-units>
              <unit-calculator>BINARY</unit-calculator>
              <unit-factor>1048576</unit-factor>
              <expiry-delay>{back-tier-expiry-delay 0}</expiry-delay>
            </local-scheme>
         </backing-map-scheme>
         <autostart>true</autostart>
    </distributed-scheme>

  </caching-schemes>
</cache-config>
```