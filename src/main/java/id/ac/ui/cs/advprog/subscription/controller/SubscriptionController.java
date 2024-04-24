package id.ac.ui.cs.advprog.subscription.controller;

import id.ac.ui.cs.advprog.subscription.model.Subscription;
import id.ac.ui.cs.advprog.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subscription")
class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/createSubscription")
    public String createSubscriptionPage(Model model) {
        Subscription subscription = new Subscription();
        model.addAttribute("Subscription", subscription);
        return "createSubscription";
    }

    @PostMapping("/createSubscription")
    public String createSubscriptionPost(@ModelAttribute Subscription subscription, Model model) {
        subscriptionService.create(subscription);
        return "redirect:listSubscription";
    }

    @GetMapping("/listSubscription")
    public String subscriptionListPage(Model model) {
        List<Subscription> allSubscription = subscriptionService.findAll();
        model.addAttribute("subscription", allSubscription);
        return "subscriptionList";
    }

    @GetMapping("/editSubscription/{subscriptionId}")
    public String editSubscriptionPage(@PathVariable String subscriptionId, Model model) {
        Subscription subscription = subscriptionService.findById(subscriptionId);
        model.addAttribute("subscription", subscription);
        return "editSubscription";
    }

    @PostMapping("/editSubscription")
    public String editSubscriptionPost(@ModelAttribute Subscription subscription, Model model) {
        System.out.println((subscription.getSubscriptionID()));
        subscriptionService.update(subscription.getSubscriptionID(), subscription);

        return "redirect:listSubscription";
    }

    @PostMapping("/deleteSubscription")
    public String deleteSubscription(@RequestParam("subscriptionId") String subscriptionId) {
        subscriptionService.deleteSubscriptionById(subscriptionId);
        return "redirect:listSubscription";
    }
}