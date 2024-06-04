Thread/Request

Reactive
 - Non blocking and Async
 - it eliminates waiting time
 - Request 
  --> (thread does not wait, perform other task if there are any) 
    --> (when response is ready, push it)



Distributed Systems
  - Resiliency
    - if 1 system failed, all will failed -- not resilient

Reactive Manifesto
  - 4 pillars -- all interconnected
    - Resilient
    - Responsive
      - responding in a timely manner
      - only when its required -- lazy
    - Message Drive
    - Elastic
      - Staying responsive under varying load
      - app does not have any bottleneck
      - throughput scales up and down base on demand